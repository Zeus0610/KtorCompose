package com.zeus.data.repository

import com.zeus.model.StreamingContent
import java.io.File

class FilesRepository {

    private val streamingContentFile = File("D:/tmp/streaming")

    fun getHomeContent(): List<StreamingContent> {
        return streamingContentFile.filterDirectories()
    }

    fun getSingleContent(contentName: String): List<StreamingContent> {
        val contentPath = File(streamingContentFile, contentName)
        val directories = contentPath.filterDirectories()
        if (directories.isEmpty()) {
            val files = contentPath.filterFiles()
            return files.map {
                StreamingContent(
                    name = it.name,
                    video = it.name,
                    isSingleContent = true,
                    image = "$contentName/image.png"
                )
            }
        } else {
            return directories
        }
    }

    fun getFile(contentName: String): File {
        val contentPath = File(streamingContentFile, contentName)
        return contentPath
    }
}

private fun File.filterDirectories(): List<StreamingContent> {
    return listFiles { file ->
        file.isDirectory
    }?.map { directory ->
        val contentFiles = directory.listFiles { content -> content.isDirectory }
        if((contentFiles?.size ?: 0) > 1) {
            StreamingContent(
                name = directory.name,
                isSingleContent = false,
                image = "${directory.name}/image.png"
            )
        } else {
            val rootContentName = if (this.name != "streaming") {
                this.name + "/"
            } else {
                ""
            }
            StreamingContent(
                name = directory.name,
                video = directory.filterFiles().firstOrNull()?.name?: "",
                isSingleContent = true,
                image = "$rootContentName${directory.name}/image.png"
            )
        }
    }.orEmpty().sortedBy { it.name }
}

private fun File.filterFiles(): List<File> {
    return listFiles { file ->
        file.isFile && file.extension.matches("(mpd|mp4)".toRegex())
    }?.toList()?.sortedBy { it.name }.orEmpty()
}
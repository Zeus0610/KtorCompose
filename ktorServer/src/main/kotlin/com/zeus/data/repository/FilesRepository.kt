package com.zeus.data.repository

import com.zeus.model.StreamingContent
import java.io.File

class FilesRepository {

    private val streamingContentFile = File("D:/tmp/streaming")

    fun getHomeContent(): List<StreamingContent> {
        return streamingContentFile.filterDirectories().map {
            StreamingContent(
                name = it.name
            )
        }
    }

    fun getSingleContent(contentName: String): List<StreamingContent> {
        val contentPath = File(streamingContentFile, contentName)
        val directories = contentPath.filterDirectories()
        if (directories.isEmpty()) {
            val files = contentPath.filterFiles()
            return files.map {
                StreamingContent(
                    name = it.name,
                    video = it.name
                )
            }
        } else {
            return directories
                .map {
                    StreamingContent(
                        name = it.name,
                        video = "${it.name}.mpd"
                    )
                }
        }
    }

    fun getFile(contentName: String): File {
        val contentPath = File(streamingContentFile, contentName)
        return contentPath
    }
}

private fun File.filterDirectories(): List<File> {
    return listFiles { file ->
        file.isDirectory
    }?.toList()?.sortedBy { it.name }.orEmpty()
}

private fun File.filterFiles(): List<File> {
    return listFiles { file ->
        file.isFile && file.extension == "mpd"
    }?.toList()?.sortedBy { it.name }.orEmpty()
}
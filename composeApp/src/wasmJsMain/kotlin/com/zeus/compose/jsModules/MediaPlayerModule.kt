package com.zeus.compose.jsModules

@JsModule("dashjs")
external object dashjs {
    fun MediaPlayer(): MediaPlayerFactory

    interface MediaPlayerFactory {
        fun create(): MediaPlayerClass
    }

    interface MediaPlayerClass {
        fun initialize(view: JsAny?, source: JsAny?, AutoPlay: JsAny, startTime: JsAny = definedExternally)
        fun play()
        fun pause()
        fun setXHRWithCredentialsForType(type: JsString, value: JsBoolean)
        fun destroy()
        fun isReady(): JsBoolean
    }
}

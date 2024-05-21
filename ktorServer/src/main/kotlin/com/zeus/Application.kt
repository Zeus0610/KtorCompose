package com.zeus

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

/*fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}*/

/*
fun Application.module() {
    configureSerialization()
    configureDatabases()
    configureSecurity()
    configureRouting()
}
*/

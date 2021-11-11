import io.ktor.application.*
import io.ktor.html.respondHtml
import io.ktor.http.HttpStatusCode
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.html.*
import org.slf4j.LoggerFactory

fun HTML.index() {
    head {
        title("Hello from Ktor!")
    }
    body {
        div {
            +"Hello from Ktor"
        }
    }
}

fun main() {
    val logger = LoggerFactory.getLogger(Application::class.java)

    embeddedServer(Netty, port = 12364, host = "192.168.0.231") {
        routing {
            get("/") {
                println("Connected")
                call.respondHtml(HttpStatusCode.OK, HTML::index)
                call.application.environment.log.info("Hi")
                logger.info("A logger0")
                logger.error("AAAAA!")
            }
        }
    }.start(wait = true)
}

fun Application.module(testing: Boolean = false) {
    log.info("Hello from module!")
}

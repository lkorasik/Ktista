import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.http.HttpStatusCode
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.html.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

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
    connect()

    embeddedServer(Netty, port = 12364, host = "192.168.0.231") {
        routing {
            get("/") {
                println("Connected")
                call.respondHtml(HttpStatusCode.OK, HTML::index)
            }
        }
    }.start(wait = true)
}

fun connect() {
    var conn: Connection? = null
    try {
        // db parameters
        val url = "jdbc:sqlite:test.db"
        // create a connection to the database
        conn = DriverManager.getConnection(url)
        println("Connection to SQLite has been established.")
    } catch (e: SQLException) {
        println(e.message)
    } finally {
        try {
            conn?.close()
        } catch (ex: SQLException) {
            println(ex.message)
        }
    }
}
import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.http.HttpStatusCode
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.html.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.reflect.Member
import java.sql.Connection

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
    Database.connect("jdbc:sqlite:test.db", "org.sqlite.JDBC")

    transaction(transactionIsolation = Connection.TRANSACTION_SERIALIZABLE, repetitionAttempts = 3)
    {
        SchemaUtils.create(Users)

        Users.insert {
            it[id] = 1
            it[firstName] = "Lev"
            it[lastName] = "Korasikovich"
        }
    }

    embeddedServer(Netty, port = 12364, host = "192.168.0.231") {
        routing {
            get("/") {
                println("Connected")
                transaction(transactionIsolation = Connection.TRANSACTION_SERIALIZABLE, repetitionAttempts = 3)
                {
                    println(Users.selectAll().first())
                }
                call.respondHtml(HttpStatusCode.OK, HTML::index)
            }
        }
    }.start(wait = true)
}

object Users: Table() {
    val id: Column<Int> = integer("id")
    val firstName: Column<String> = text("first_name")
    val lastName: Column<String> = text("last_name")
}
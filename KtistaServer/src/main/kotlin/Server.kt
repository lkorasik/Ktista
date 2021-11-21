import com.fasterxml.jackson.databind.SerializationFeature
import com.lkorasik.auth.Login
import com.lkorasik.jwt.JWT
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.html.respondHtml
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.html.*
import java.lang.RuntimeException
import java.time.format.DateTimeFormatter
import java.util.*

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
    embeddedServer(Netty, port = 12364, host = "192.168.0.231") {
        val simpleJwt = JWT("my-super-puper-secret-key-for-jwt")

        install(StatusPages) {
            exception<RuntimeException> { exception ->
                call.respond(HttpStatusCode.Unauthorized, mapOf("OK" to false, "error" to (exception.message ?: "")))
            }
        }
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT) // Pretty Prints the JSON
            }
        }
        install(Authentication) {
            jwt {
                verifier(simpleJwt.verifier)
                validate {
                    UserIdPrincipal(it.payload.getClaim("name").asString())
                }
            }
        }

        routing {
            post("/login") {
                val post = call.receive<Login>()
                val user = users[post.name]
                if (user?.password != post.password)
                    throw RuntimeException("Invalid credentials")
                call.respond(mapOf("token" to simpleJwt.sign(user.name)))
            }
            post("/reg"){
                val post = call.receive<Login>()
                if(users.containsKey(post.name))
                    throw RuntimeException("User already has")
                users[post.name] = Login(post.name, post.password)
                call.respond(mapOf("token" to simpleJwt.sign(post.name)))
            }
            get("/") {
                println("Connected")
                call.respondHtml(HttpStatusCode.OK, HTML::index)
            }
            route("/info") {
                authenticate {
                    get {
                        call.respond(Calendar.getInstance().time)
                    }
                }
            }
        }
    }.start(wait = true)
}

val users: MutableMap<String, Login> = Collections.synchronizedMap(
    listOf(Login("test", "test"))
        .associateBy { it.name }
        .toMutableMap()
)
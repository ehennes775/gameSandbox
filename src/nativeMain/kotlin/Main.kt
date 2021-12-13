import kotlinx.cinterop.*
import kotlinx.cinterop.nativeHeap.alloc
import platform.posix.poll
import sdl2.*
import kotlin.system.exitProcess

fun main() {

    val status = SDL_Init(SDL_INIT_VIDEO)

    if (status != 0) {
        println("Unable to initialize SDL")
        exitProcess(-1)
    }

    println("SDL Kotlin/Native")

    var window = SDL_CreateWindow(
        "SDL Kotlin/Native",
        SDL_WINDOWPOS_UNDEFINED.toInt(),
        SDL_WINDOWPOS_UNDEFINED.toInt(),
        1024,
        768,
        SDL_WINDOW_SHOWN
    )

    memScoped {
        var event = alloc<SDL_Event>()
        println("Created event")
        var pollStatus = SDL_PollEvent(event.ptr)
        println("Got first event")
        while (pollStatus > 0) {
            println("entering loop pollStatus=${pollStatus} event.type=${event.type}")
            when (event.type) {
                SDL_QUIT -> break
                SDL_MOUSEBUTTONUP -> break
                SDL_KEYUP -> break
            }
            pollStatus = SDL_PollEvent(event.ptr)
            println("Got next event")
        }
        println("Exited loop pollStatus=${pollStatus}")
    }

    SDL_DestroyWindow(window)

    SDL_Quit()
}
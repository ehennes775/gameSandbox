import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import sdl2.*
import kotlin.system.exitProcess

fun main() {

    val status = SDL_Init(SDL_INIT_VIDEO)

    if (status != 0) {
        println("Unable to initialize SDL")
        exitProcess(-1)
    }

    var window = SDL_CreateWindow(
        "SDL Kotlin/Native",
        SDL_WINDOWPOS_UNDEFINED.toInt(),
        SDL_WINDOWPOS_UNDEFINED.toInt(),
        1024,
        768,
        SDL_WINDOW_SHOWN
    )

    memScoped {
        while (true) {
            var event = alloc<SDL_Event>()

            var pollStatus = SDL_PollEvent(event.ptr)

            while (pollStatus == 0) {
                SDL_Delay(100)
                pollStatus = SDL_PollEvent(event.ptr)
            }

            when (event.type) {
                SDL_QUIT -> break
                SDL_MOUSEBUTTONUP -> break
                SDL_KEYUP -> break
            }
        }
    }

    SDL_DestroyWindow(window)

    SDL_Quit()
}
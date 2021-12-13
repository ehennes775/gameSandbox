import kotlinx.cinterop.*
import sdl2.*

fun main() {
    val status = SDL_Init(SDL_INIT_EVERYTHING)
    SDL_Quit()
}
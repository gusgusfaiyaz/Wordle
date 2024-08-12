Adivina la Palabra
Este es un juego de adivinanza de palabras implementado en Java usando la biblioteca Swing. El objetivo del juego es adivinar una palabra de 5 letras en un máximo de 6 intentos. Cada vez que introduces una palabra, el juego proporciona retroalimentación sobre las letras, indicando si están en la posición correcta, si están en la palabra pero en la posición incorrecta, o si no están en la palabra en absoluto.

Requisitos
Java Development Kit (JDK) 8 o superior.
IDE recomendado: IntelliJ IDEA, Eclipse, NetBeans, o cualquier otro que soporte proyectos Java.
Clonar el repositorio (opcional)

Cómo jugar
Iniciar el Juego: Al iniciar el juego, se te pedirá que ingreses una palabra de 5 letras.

Retroalimentación:

Verde: La letra está en la palabra correcta y en la posición correcta.
Amarillo: La letra está en la palabra, pero en una posición incorrecta.
Gris: La letra no está en la palabra.
Intentos: Tienes 6 intentos para adivinar la palabra correcta. Si fallas, se te mostrará la palabra correcta.

Reiniciar Juego: Si deseas volver a jugar, haz clic en el botón "Reiniciar".

Estructura del Código
Palabra.java: Clase principal que maneja la lógica del juego.
Teclado.java: Clase que representa el teclado en pantalla.
JuegoGUI.java: Clase encargada de inicializar y manejar la interfaz gráfica del juego.
main.java: Clase que contiene el método main para iniciar la aplicación.

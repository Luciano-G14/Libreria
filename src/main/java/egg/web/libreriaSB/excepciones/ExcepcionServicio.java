/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreriaSB.excepciones;

/**
 *
 * @author Luciano
 */
public class ExcepcionServicio extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionServicio</code> without detail
     * message.
     */
    public ExcepcionServicio() {
    }

    /**
     * Constructs an instance of <code>ExcepcionServicio</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionServicio(String msg) {
        super(msg);
    }
}

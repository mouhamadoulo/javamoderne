package com.molo.p5exceptions;

/**
 * Une exception personnalisée pour signaler un retrait impossible.
 * Exception checked : le compilateur impose sa gestion.
 * À retenir :
 * - extends Exception : crée une exception checked, vérifiée à la compilation.
 * - super(message) : transmet le message à la classe parente.
 * - extends RuntimeException aurait créé une exception unchecked.
 */
public class SoldeInsuffisantException extends Exception {

    public SoldeInsuffisantException(String message) {
        super(message);
    }
}

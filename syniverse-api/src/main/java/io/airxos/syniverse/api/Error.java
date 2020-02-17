/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.airxos.syniverse.api;

/**
 *
 * @author jaase
 */
public class Error {

    static public class ServerFailure extends ScgException {

        private final String reason;
        private final Integer errcode;

        ServerFailure(Integer errcode, final String reason) {
            this.reason = reason;
            this.errcode = errcode;
        }

        @Override
        public String toString() {
            return "Error: " + errcode + " " + reason;
        }

        public String getReason() {
            return reason;
        }

        public Integer getErrcode() {
            return errcode;
        }
    }

    static public class NoResourceAttached extends ScgException  {}

    static public class AuthError extends ScgException {}
}

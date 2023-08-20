package com.betrybe.museumfinder.exception;

/**
 * Classe de exceção para coordenadas inválidas.
 */

public class InvalidCoordinateException extends RuntimeException {
  public InvalidCoordinateException(String message) {
    super(message);
  }
}
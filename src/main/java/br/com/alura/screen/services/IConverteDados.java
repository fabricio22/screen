package br.com.alura.screen.services;

public interface IConverteDados {
  <T> T obterDados(String json, Class<T> classe);
}

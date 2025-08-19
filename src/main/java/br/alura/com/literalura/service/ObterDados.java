package br.alura.com.literalura.service;

public interface ObterDados {
    <T> T obterDados(String json, Class<T> classe);
}

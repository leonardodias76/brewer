package com.algaworks.brewer.storage;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FotoStorage {

	String THUMBNAIL_PREFIX = "thumbnail.";

	String salvar(MultipartFile[] files);

	byte[] recuperar(String foto);

	byte[] recuperarThumbnail(String fotoCerveja);

	void excluir(String foto);

	String getUrl(String foto);

	default String renomearArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_" + nomeOriginal;
	}

}
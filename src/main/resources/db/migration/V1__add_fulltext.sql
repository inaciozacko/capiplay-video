ALTER TABLE video ADD FULLTEXT INDEX video_search (titulo);
ALTER TABLE tag ADD FULLTEXT INDEX tag_search (tag);
ALTER TABLE categoria ADD FULLTEXT INDEX categoria_search (categoriaString);

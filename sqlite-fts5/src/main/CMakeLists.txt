cmake_minimum_required(VERSION 2.8)
include(ExternalProject)

ExternalProject_Add(sqlite-sources
	URL ${SQLITE_ZIP}
	PREFIX ${CMAKE_CURRENT_BINARY_DIR}/sqlite-sources
	CONFIGURE_COMMAND cd ../sqlite-sources && ./configure
	BUILD_COMMAND ""
	INSTALL_COMMAND "")

ExternalProject_Get_Property(sqlite-sources install_dir)

add_custom_command(
	OUTPUT fts5.c sqlite3.h sqlite3ext.h
	DEPENDS ${install_dir}
	COMMAND make fts5.c
	COMMAND cp fts5.c ${CMAKE_CURRENT_BINARY_DIR}
	COMMAND make sqlite3.c
	COMMAND cp sqlite3.h ${CMAKE_CURRENT_BINARY_DIR}
	COMMAND cp sqlite3ext.h ${CMAKE_CURRENT_BINARY_DIR}
	WORKING_DIRECTORY ${install_dir}/src/sqlite-sources
	)

add_custom_target(gen-sources DEPENDS fts5.c sqlite3.h sqlite3ext.h)
add_library(sqlite-fts5 SHARED sqlite3.h fts5.c sqlite3ext.h)
add_dependencies(sqlite-fts5 gen-sources)
$ cd target/generated-sources/archetype/
$ mvn install

$ mkdir /tmp/archetype
$ cd /tmp/archetype
$ mvn archetype:generate -DarchetypeCatalog=local

$ mvn archetype:create-from-project

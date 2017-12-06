algemeen
--------

- wijziging 1
- wijziging 2
- wijziging 3
- wijziging 4

- wijziging 0612

jgitflow-maven-plugin
---------------------

LA LA LA LA LA

https://gist.github.com/lemiorhan/97b4f827c08aed58a9d8

1a) create repo op github & do local git init
1b) push master, maak develop aan en push

2) gebruik jgitflow:
2a) mvn jgitflow:feature-start
2b) push en verifieer in github
2c) maak nog wat wijzigingen
2c1) mvn jgitflow:feature-finish -> error
2c2) push + mvn jgitflow:feature-finish -> OK

3a) controleer branch : zou develop moeten zijn
3b) push nog een keer

git & gitflow
-------------

https://github.com/nvie/gitflow

to install: https://github.com/nvie/gitflow/wiki/Windows

important
---------

* NO dependencies for JAXB!!!

xjc -p be.vdab.training.domain src/main/resources/Contact.xsd -d src/main/java

probleem Contact_wim.xsd -> geen root element (FIXED)

Altova: https://www.altova.com/xmlspy-xml-editor/xsd-editor
http://www.stylusstudio.com/xsd-editor.html



[![Build Status](https://ligi.ci.cloudbees.com/job/AXT/badge/icon)](https://ligi.ci.cloudbees.com/job/AXT/)


Why?
====

The aim of this project is to provide code that is used in a lot of Android-Apps over and over again this way:

 * reduce code in the apps ( -> more readable )
 * deduplicate code and	tests ( -> save space & time )

How?
===

You can either [download the jar](https://ligi.ci.cloudbees.com/job/AXT/lastSuccessfulBuild/artifact/build/libs/) and use it - or pull the lib via maven - this is how this is done in gradle:

```groovy
repositories {
    maven {
        url "https://raw.github.com/ligi/ligis-mvn-repo/master"
    }
}

dependencies {
    compile 'org.ligi:AXT:0.20'
}

```

most helpers are invoked that way

```java
 // context
 EditText editText = getEditText();
 
 // invoke AXT
 AXT.at(editText).changeTextIfNeeded("new text?");
```

Why the Name?
=============

the acronymes comes from **A**ndroid e<b>XT</b>ensions and also leaned torwards dagger and butterknife that aim on reducing code in android apps - AXT is german for AXE - so also a cutting instrument.


Terms
=====


This library is released under MIT license. Feel free to use it. Pull requests are welcome.


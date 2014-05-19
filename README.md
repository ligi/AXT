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
    compile 'org.ligi:AXT:0.26'
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

the acronymes comes from <b>A</b>ndroid e<b>XT</b>ensions and also leaned torwards dagger and butterknife that aim on reducing code in android apps - AXT is german for AXE - so also a cutting tool.


Terms
=====

This library is released under Apache 2 license. Feel free to use it. Pull requests are welcome.


    Copyright 2013 ligi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

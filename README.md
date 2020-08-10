# Developer handbook

## How to Run Ubuntu GUI in WSl

#### Step 1: install a Graphic user interface, such as XFCE4 desktop (As this is fastest). Run Following commands for that.

   sudo apt-get install xfce4 xorg xrdp -y \
   sudo sed -i 's/port=3389/port=3390/g' /etc/xrdp/xrdp.ini

#### Create and modify .xsession

  cd /home/user/ \
  sudo nano .xsession

#### Add argument for gnome sesison:

  gnome-session --session=ubuntu-2d

#### Change permission for xession and enable xrdp

  sudo chmod 755 .xsession \
  sudo systemctl enable xrdp

#### Change default startwm.sh

  cd /etc/xrdp \
  sudo chmod 777 startwm.sh \
  sudo nano startwm.sh

#### In above opened file, Comment out the bottom two lines

  #test -x /etc/X11/Xsession && exec /etc/X11/Xsession \
  #exec /bin/sh /etc/X11/Xsession

#### Add below command and exit:

  startxfce4

#### In Windows, restart Ubuntu and run in new restardetd terminal:
  sudo /etc/init.d/xrdp start

#### open new ubuntu console from windows 10 type 

  sudo apt install xfce4, hit enter \
  sudo apt update, hit enter

#### Step2: Install the X server in windows 10. I used an open-source server called VcXsrv. 
It can be downloaded from https://sourceforge.net/projects/vcxsrv/.
Once the installation is completed:

open XLauch in windows 10

perform the following setup:

select: "One large window"
select Display number: 0
click next
select Start no client
click next
click finish

#### Step 3: setup in Linux console:

export DISPLAY=:0 \

export LIBGL_ALWAYS_INDIRECT=1

#### Step 4: Display xfce4 GUI:

type startxfce4 , and press enter.

#### Step 5: Install intellij now on the GUI the normal linux installation way

### Note: Add below to bat file and run that every time, or just the exe attached

CD "C:\Program Files\VcXsrv" \
START vcxsrv.exe :0 -clipboard -wgl \
bash.exe -c "export DISPLAY=:0 && export PULSE_SERVER=tcp:localhost && xfce4-session" \
PAUSE

## Requirements and setup

- Local installation of JDK 8.x
- Local installation of Scala 2.11.x (exact minor version, 2.12.x not supported)
- Local installation of Maven 3.5.x ++ (at least some version like this)
- IDE like IntelliJ (Eclipse can also be used, but we recommend IntelliJ since it reacts better with Scala code integration)

### Required IntelliJ plugins

- Scala Plugin

### Required Maven settings

Maven will require a setting to authenticate against Artifactory, for this add this file ```~/.m2/settings.xml``` (or add the contents if the file already exists):
The recommended way is to have the encrypted password in settings.xml. Look [here](https://maven.apache.org/guides/mini/guide-encryption.html) for more info.
```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                  http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <proxies>
    <proxy>
  <id>1</id>
  <active>true</active>
      
      <protocol>http</protocol>
      <host>de001-surf.zone2.proxy.allianz</host>
      <port>8090</port>
    </proxy>

    <proxy>
  <id>2</id>
  <active>true</active>
      
      <protocol>https</protocol>
      <host>de001-surf.zone2.proxy.allianz</host>
      <port>8090</port>
    </proxy>
  </proxies>
</settings>

```
#### Maven Intellij Proxy setup (if above doesn't work)
Go to settings->maven->importing->change VM options to -> "-Xmx768m  -DproxySet=true -DproxyHost=de001-surf.zone2.proxy.allianz -DproxyPort=8090" without quotes
also, change same in runner for maven VM options

### Using this repository within your IDE

The repository and all modules can easily be opened inside your IDE by opening the root folder that contains the root [pom.xml](pom.xml).
All content will ideally directly be imported into your IDE.
If some pop-up dialogues appear regarding Maven select "Automatic" for importing Maven changes.
If your Maven is set up correctly, the project should be able to build directly.

## Working with the project

**Warning:** do not use multi-threads for parallelizing the build ```mvn -T 2C ...``` .

### Building the whole project
* #### it is just a maven project
```
mvn clean package
```
* #### Unit tests only (include coverage check)
```
mvn test
```
or
```
mvn clean test
```
* #### Integration tests as well
```
mvn integration-test
```
or
```
mvn clean integration-test
```
* #### Skip all tests
```
mvn <install/package/....> -DskipTests -Dintegration.skipTests -Dscoverage.skip -Djacoco.skip
```
* #### Skip integration tests
```
mvn <install/package/....> -Dintegration.skipTests
```
* #### Skip unit tests
```
    mvn <install/package/....> -DskipTests -Dscoverage.skip -Djacoco.skip
```
### Building specific sub-module only

Setup your authentication to the artifactory (see "Required Maven settings"). The current snapshot artifacts are stored 
in the artifactory and maven will download them if necessary.

Alternative 1: run with ```mvn install``` at least once after updating the build version in ```.mvn/maven.config```, 
so your local Maven repository will contain the parent pom files and artifacts.

Alternative 2: use ```mvn -am ...``` for building also the libraries a subcomponent is depending on, e.g.

#### Java
If you want to run only tests:
`mvn clean test`



### Changes to Maven .pom files

Please be aware that this project uses a inheritance and aggregation schema for Maven that is common for larger Maven projects. 
To place a new module, dependency (that was not yet used), change or add new Maven plugins, 
please check always where to put it right instead of just adding it to you modules pom.xml file.

#### Project version

The project version is stored in the `.mvn/maven.config` file. 

Use `mvn <install/package/etc.> -Drevision=<your version> ...` to adapt the version in your local build.

### Spark Submit Sample:

spark-submit  --master local   --class com.example.spark.data.engineering.models.layer.coreontology.jobs.CoreOntologyBatchJob models-layer-2.0-SNAPSHOT-jar-with-dependencies.jar --host "" --port "" --username "" --password "" --service_name "" --output_path ""

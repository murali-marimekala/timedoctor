@echo off
rem set ECLIPSE_BASE_LOCATION=
rem set BUILD_LAUNCH_CMD=
set BUILDTYPE=I
set BUILDID=TestBuild
set SKIPFETCH=true
set CONFIG="win32, win32, x86 & linux, gtk, x86"
rem set ARCHIVESFORMAT="win32, win32, x86 - Folder"

rem YOU MAY NOT NEED TO EDIT ANYTHING BELOW THIS
set BUILD_DIRECTORY=%CD%/build
set PLUGIN_PATH=%CD%/build
set BUILDER=%CD%/configuration
set BUILDFILE=%CD%/build.xml
set PRODUCT_PATH=%PLUGIN_PATH%/features/net.timedoctor.feature.workbench/workbench.product
set JAVACSOURCE=1.5
set JAVACTARGET=1.5

%BUILD_LAUNCH_CMD% -application org.eclipse.ant.core.antRunner -Dconfigs=%CONFIG% -DarchivePrefix=TimeDoctor -DoutputUpdateJars=false -DbuildDirectory=%BUILD_DIRECTORY% -Dbuilder=%BUILDER% -Dproduct=%PRODUCT_PATH% -DbaseLocation=%ECLIPSE_BASE_LOCATION% -DbuildId=%BUILDID% -DbuildType=%BUILDTYPE% -DskipFetch=%SKIPFETCH% -DpluginPath=%PLUGIN_PATH% -DjavacSource=%JAVACSOURCE% -DjavacTarget=%JAVACTARGET% -buildfile %BUILDFILE% > buildOutput.log 2>&1


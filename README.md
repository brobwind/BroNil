# BroNil
A simple local http proxy server run in Android device

## Build BroNil APK
	$ mkdir -pv /local/android-5.1.1_r15 && cd /local/android-5.1.1_r15
	$ repo init -u https://android.googlesource.com/platform/manifest -b android-5.1.1_r15
	$ repo sync
	$ . build/envsetup.sh
	$ lunch aosp_arm-eng
	$ cd packages/apps
	$ git clone https://github.com/brobwind/BroNil.git
	$ cd BroNil && mma -j 8

 After all the APK will be installed in:
	/loca/android-5.1.1_r15/out/target/product/generic/data/app/BroNil/BroNil.apk

## Usage
 Launch App and you will see the local proxy server in tee main screen
 Open Wifi settings:
   a. Select a connected wifi to modify its network
   b. In advanced options, use manual proxy settings
   c. Set proxy name to "127.0.0.1" or "localhost" and port to 8182
	
 Open Chrome, check the proxy settings by:
	chrome://net-internals/#proxy

 To check which url Chrome just connect by:
	adb logcat | grep ProxyServer

## Others
 Please visit:
	http://www.brobwind.com/archives/708

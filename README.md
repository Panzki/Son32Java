# Son32Java - A Java wrapper for the Son32 library

This library is a simple wrapper for the [son32.dll](http://ced.co.uk/de/upgrades/spike2tools). The wrapper is based on the [jna project](https://github.com/java-native-access/jna). The wrapper is not complete, a lot of the orginal SON32 functions are missing, but the basics (reading from a .smr file) are implemented.

##How to use?

This library **only** works with the **32bit jvm on windwos**! 

First install the [son32.dll](http://ced.co.uk/de/upgrades/spike2tools) in the deafult path (C:\SON Library). Then go ahead and download the jna.jar and jna-platform.jar from the [jna project](https://github.com/java-native-access/jna) and add them to the imports of your java project.

The following example will read the first second from channel 0. This assumes, that channel 0 is a real data channel.

		import son32java.son32reader.Son32Reader;
		import son32java.son32reader.Son32Channel;
		import son32java.son32Exceptions.*;

        String path = "path to your .smr file"
        Son32Reader reader = new Son32Reader(path, 1);
        try{
            Son32Channel channel = reader.getChannel(0);
            int array_size = channel.calculateArraySizeByTime(1);
            double[] target = new double[array_size];
            channel.getRealDataByTime(0, 1, target);
            for(int i=0;i<x;i++){
                System.out.println(target[i]);
            }            
        } catch(Exception e){
            System.out.println(e);
        }
        reader.SONCloseFile();

## Support
If you have any problems, want more features added or of you have found a bug, feel free to open an issue, write a message on github or open a pull request.
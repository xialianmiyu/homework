package pattern4_proxy.myproxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import static java.lang.System.out;


public class MyClassLoader extends ClassLoader {

    private File classPathFile;

    public MyClassLoader() {
        String classPath = MyClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(classPath);
    }

    @Override
    protected Class<?> findClass(String name) {

        String className = MyClassLoader.class.getPackage().getName() + "." + name;
        if (classPathFile == null) {
            return null;
        }
        File classFile = new File(classPathFile, name.replaceAll("\\.", "/") + ".class");
        if (!classFile.exists()) {
            return null;
        }

        try (FileInputStream in = new FileInputStream(classFile);
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {


            byte[] buff = new byte[1024];
            int len;
            while ((len = in.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
            return defineClass(className, out.toByteArray(), 0, out.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}


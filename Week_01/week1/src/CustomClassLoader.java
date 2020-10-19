import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class CustomClassLoader extends ClassLoader {

    private String byeCodePath;

    public CustomClassLoader(String byeCodePath) {
        this.byeCodePath = byeCodePath;
    }

    public CustomClassLoader(ClassLoader parent, String byeCodePath) {
        super(parent);
        this.byeCodePath = byeCodePath;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {


        CustomClassLoader customClassLoader=new CustomClassLoader("/Users/xieyinyi/Documents/code/JAVA-000/Week_01/week1/src/");
        Class hello=customClassLoader.loadClass("Hello");
        Object o=hello.newInstance();
        hello.getMethod("hello").invoke(o);

    }


    @Override
    protected Class<?> findClass(String name){
        String fileName=byeCodePath+name+".xlass";
        BufferedInputStream bis=null;
        ByteArrayOutputStream baos=null;

        try {
            bis=new BufferedInputStream(new FileInputStream(fileName));
            baos=new ByteArrayOutputStream();
            int len;
            byte[] data=new byte[1024];
            while ((len=bis.read(data))!=-1){
                baos.write(data,0,len);
            }
            byte[] bytes=baos.toByteArray();
            for(int i=0;i<bytes.length;i++){
                bytes[i]=(byte)(255-bytes[i]);
            }

            return defineClass(null,bytes,0,bytes.length);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(baos!=null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }



}

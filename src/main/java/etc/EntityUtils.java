package etc;


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Класс, описывающий утилиты тестирования
 *
 * @author Ya
 */
public class EntityUtils {

    private static AtomicLong sequence = new AtomicLong(90000);



    /**
     * Заполнение свойств сущности случайным значениями для Integer, Long, Date, String
     * @param <T> t  класс инстанс которого будет создан
     * @param isIdGen заполнять ли поле отмеченное @javax.persistence.Id
     * @return инстанс класса Т
     */
    public static <T> T fillPrimitiveProperties(Class<T> t, boolean... isIdGen) {

        try {


            T result = t.newInstance();

            Field idField = null;

            Field[] fields = result.getClass().getDeclaredFields();
            for (Field field : fields) {
                Annotation[] annotations = field.getAnnotations();
                for (Annotation annotation : annotations) {
                    if(annotation.annotationType().getCanonicalName().equals(Id.class.getCanonicalName())){
                        // System.out.println("ann=" + annotation+  " field = " + field.getName() );
                        idField = field;

                    }
                }
            }
            BeanInfo beanInfo = Introspector.getBeanInfo(t);
            for (PropertyDescriptor propDescriptor : beanInfo.getPropertyDescriptors()) {

                Method method = propDescriptor.getWriteMethod();
                if (method == null) continue;
                if(idField != null && method.getName().equals("set" + upperCaseFirstLetter(idField.getName()))) {

                    if(isIdGen.length > 0 && isIdGen[0]) {
                        method.invoke(result, sequence.incrementAndGet());
                    }

                    continue;
                }


                Class<?>[] types = method.getParameterTypes();
                for (Class<?> type : types) {


                    if (type.getName().equals(Boolean.class.getName())) {

                        method.invoke(result, Boolean.TRUE);
                    }
                    if (type.getName().equals(Long.class.getName())) {

                        method.invoke(result, sequence.incrementAndGet());
                    }
                    if (type.getName().equals(Integer.class.getName())) {

                        method.invoke(result, (int) sequence.incrementAndGet());
                    }
                    if (type.getName().equals(String.class.getName())) {

                        method.invoke(result, "Ф_" +
                                lowerCaseFirstLetter(method.getName().substring(3)) + "_" + sequence.incrementAndGet());

                    }

                    if (type.getName().equals(Date.class.getName())) {
                        method.invoke(result, new Date());
                    }
                }
            }
            return result;
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException  | IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Сделать заглавной первую букву строки
     * @param sString
     * @return
     */
    public static String upperCaseFirstLetter(String sString){
        return Character.toString(sString.charAt(0)).toUpperCase()+sString.substring(1);
    }

    /**
     * Сделать строчной первую букву строки
     * @param sString
     * @return
     */
    public static String lowerCaseFirstLetter(String sString){
        return Character.toString(sString.charAt(0)).toLowerCase()+sString.substring(1);
    }

    /**
     * Получение следующего значения последовательности
     * @return
     */
    public static Long nextFromSequence() {
        return sequence.incrementAndGet();
    }

    /**
     * Вызов метода через Reflection API
     *
     * @param methodName
     * @param object
     * @param params
     * @return
     */
    public static Object invokeMethod(String methodName, Object object,Object ...params) {

        Class[] clazz = new Class[params.length];

        for (int i = 0; i < params.length; i++) {
            clazz[i] = params[i].getClass();
        }


        try {
            Method m = object.getClass().getMethod(methodName, clazz);
            Object ret = m.invoke(object, params);

            return ret;

        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    public static  @interface Id {
    }
}



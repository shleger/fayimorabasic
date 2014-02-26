package etc;

/**
 * Класс, описывающий
 *
 * @author shleger
 */

import org.apache.commons.beanutils.PropertyUtils;

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
 * @author shleger
 */
public class EntityUtils {

    public static AtomicLong sequence = new AtomicLong(0);



    /**
     * Заполнение свойств сущности случайным значениямо для Integer, Long, Date, String
     *
     * @param <T> t  класс инстанс которого будет создан
     * @return T инстанс класса Т
     * @throws IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     * @throws InstantiationException
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

            for (PropertyDescriptor propDescriptor : PropertyUtils.getPropertyDescriptors(result)) {

                Method method = propDescriptor.getWriteMethod();
                if (method == null) continue;
                if(idField != null && method.getName().equals("set" + upperCaseFirstLetter(idField.getName()))) {

                    if(isIdGen.length > 0 && isIdGen[0]) { //TODO
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

                        method.invoke(result, "поле_" +
                                lowerCaseFirstLetter(method.getName().substring(3)) + "_" + sequence.incrementAndGet());

                    }

                    if (type.getName().equals(Date.class.getName())) {
                        method.invoke(result, new Date());
                    }
                }
            }
            return result;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static String upperCaseFirstLetter(String sString){
        return Character.toString(sString.charAt(0)).toUpperCase()+sString.substring(1);
    }
    public static String lowerCaseFirstLetter(String sString){
        return Character.toString(sString.charAt(0)).toLowerCase()+sString.substring(1);
    }

    public static AtomicLong getSequence() {
        return sequence;
    }

    @Retention(RetentionPolicy.RUNTIME)
    public static  @interface Id {
    }
}


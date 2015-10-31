import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ayush on 10/9/15.
 */
public class HashCodeTest {

    int size = 10; //default unless changed

    @Test
    public void testSet1() throws Exception {
        HashCode hashCode = new HashCode(size);
        String key1 = "a";
        String value = "test";
        hashCode.set(key1, value);
        Assert.assertEquals(hashCode.get(key1), value);
        Assert.assertEquals(hashCode.load(), (float)1.0/size);
    }

    @Test
    public void testSet2() throws Exception {
        HashCode hashCode = new HashCode(size);
        String key1 = "a";
        String key2 = "k"; //overlaps with a.
        String value1 = "test1";
        String value2 = "test2";
        Assert.assertEquals(hashCode.set(key1, value1), true);
        Assert.assertEquals(hashCode.set(key2, value2), true);
        Assert.assertEquals(hashCode.get(key1), value1);
        Assert.assertEquals(hashCode.get(key2), value2);
        Assert.assertEquals(hashCode.load(), (float)2.0/size);
        //Inter changing vaues of key1 and key2.
        Assert.assertEquals(hashCode.set(key1, value2), true);
        Assert.assertEquals(hashCode.set(key2, value1), true);
        Assert.assertEquals(hashCode.get(key1), value2);
        Assert.assertEquals(hashCode.get(key2), value1);
        Assert.assertEquals(hashCode.load(), (float)2.0/size);
    }


    @Test
    public void testDelete() throws Exception {
        HashCode hashCode = new HashCode(size);
        String key1 = "a";
        String key2 = "k";  //Same index as a.
        String key3 = "b";  //Same index as k.
        String key4 = "l";  //Same index as k.
        String value1 = "test1";
        String value2 = "test2";
        //Inserting entries into hashmap
        Assert.assertEquals(hashCode.set(key1, value1), true);
        Assert.assertEquals(hashCode.set(key2, value2), true);
        Assert.assertEquals(hashCode.set(key3, value1), true);
        Assert.assertEquals(hashCode.set(key4, value2), true);
        //Checking for all entries
        Assert.assertEquals(hashCode.get(key1), value1);
        Assert.assertEquals(hashCode.get(key2), value2);
        Assert.assertEquals(hashCode.get(key3), value1);
        Assert.assertEquals(hashCode.get(key4), value2);
        Assert.assertEquals(hashCode.load(), (float) 4.0 / size);
        //deleting one element
        Assert.assertEquals(hashCode.delete(key1), value1);
        //Checking for all other elements
        Assert.assertEquals(hashCode.get(key1), null);
        Assert.assertEquals(hashCode.get(key2), value2);
        Assert.assertEquals(hashCode.get(key3), value1);
        Assert.assertEquals(hashCode.get(key4), value2);
        Assert.assertEquals(hashCode.load(), (float) 3.0 / size);
        Assert.assertEquals(hashCode.delete(key1), null);
        //Checking for all other elements
        Assert.assertEquals(hashCode.get(key1), null);
        Assert.assertEquals(hashCode.get(key2), value2);
        Assert.assertEquals(hashCode.get(key3), value1);
        Assert.assertEquals(hashCode.get(key4), value2);
        Assert.assertEquals(hashCode.load(), (float)3.0/size);
    }

    @Test
    public void testOverflow() throws Exception {
        HashCode hashCode = new HashCode(size);
        for(int i=0; i<15; i++){
            if(i<10)
                Assert.assertEquals(hashCode.set(String.valueOf(i), i), true);
            else
                Assert.assertEquals(hashCode.set(String.valueOf(i), i), false);
        }
        for(int i=0; i<15; i++){
            if(i<10)
                Assert.assertEquals(hashCode.get(String.valueOf(i)), i);
            else
                Assert.assertEquals(hashCode.get(String.valueOf(i)), null);
        }
        Assert.assertEquals(hashCode.load(), (float)1.0);
        for(int i=0; i<15; i++){
            if(i<10)
                Assert.assertEquals(hashCode.set(String.valueOf(i), 15 * i), true);
            else
                Assert.assertEquals(hashCode.set(String.valueOf(i), 15 * i), false);
        }
        for(int i=0; i<15; i++){
            if(i<10)
                Assert.assertEquals(hashCode.get(String.valueOf(i)), 15*i);
            else
                Assert.assertEquals(hashCode.get(String.valueOf(i)), null);
        }
        Assert.assertEquals(hashCode.load(), (float)1);
    }

    @Test
    public void testLoad() throws Exception {
        HashCode hashCode = new HashCode(size);
        for(int i=0; i<15; i++){
            if(i<10)
                Assert.assertEquals(hashCode.set(String.valueOf(i), i), true);
            else
                Assert.assertEquals(hashCode.set(String.valueOf(i), i), false);
        }
        for(int i=0; i<15; i++){
            if(i<10)
                Assert.assertEquals(hashCode.get(String.valueOf(i)), i);
            else
                Assert.assertEquals(hashCode.get(String.valueOf(i)), null);
        }
        Assert.assertEquals(hashCode.load(), (float)1);
        for(int i=0; i<5; i++){
            Assert.assertEquals(hashCode.delete(String.valueOf(i)), i);
        }
        for(int i=15; i<30; i++){
            if(i<20)
                Assert.assertEquals(hashCode.set(String.valueOf(i), i), true);
            else
                Assert.assertEquals(hashCode.set(String.valueOf(i), i), false);
        }
        for(int i=0; i<15; i++){
            if(((i>=5)&&(i<10))||((i>=15)&&(i<19)))
                Assert.assertEquals(hashCode.get(String.valueOf(i)), i);
            else
                Assert.assertEquals(hashCode.get(String.valueOf(i)), null);
        }
        Assert.assertEquals(hashCode.load(), (float)1);
        for(int i=0; i<30; i++){
            hashCode.delete(String.valueOf(i));
        }
        for(int i=0; i<30; i++){
            Assert.assertEquals(hashCode.get(String.valueOf(i)), null);
        }
    }
}

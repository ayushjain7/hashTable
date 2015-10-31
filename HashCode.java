/**
 * Created by ayush on 10/9/15.
 */
public class HashCode {
    private String[] keys;
    private Object[] values;
    private int size;
    private int numberOfElements;

    public HashCode(int size){
        this.size = size;
        this.keys = new String[size];
        this.values = new Object[size];
    }

    public boolean set(String key, Object value){
        int index = findIndex(key);
        if(index == -1)
            return false;
        if(!key.equals(this.keys[index]))
            this.numberOfElements++;
        this.keys[index] = key;
        this.values[index] = value;
        return true;
    }

    public Object get(String key){
        int index = findIndex(key);
        if(index == -1 || !key.equals(this.keys[index]))
            return null;
        return values[index];
    }

    public Object delete(String key){
        int index = findIndex(key);
        if(index == -1 || !key.equals(this.keys[index]))
            return null;
        this.keys[index]=null;
        numberOfElements--;
        Object toRet = this.values[index];
        rehash(index);
        return toRet;
    }

    private void rehash(int index) {
        int loopIndex = index >= 9 ? 0 : index+ 1;
        while(this.keys[loopIndex]!=null && loopIndex != index){
            int rehashIndex = findIndex(this.keys[loopIndex]);
            if(rehashIndex != loopIndex){
                this.keys[rehashIndex] = this.keys[loopIndex];
                this.values[rehashIndex] = this.values[loopIndex];
                this.keys[loopIndex] = null;
            }
            loopIndex = loopIndex < this.size -1 ? loopIndex + 1 : 0;
        }
    }

    public float load(){
        return (float)numberOfElements/size;
    }

    private int findIndex(String key){
        int hashC = key.hashCode();
        int index = hashC%size;
        int count = 1;
        while(this.keys[index]!=null && !this.keys[index].equals(key)){
            count++;
            index++;
            if(index > this.size-1)
                index=0;
            if(count >= this.size)
                return -1;
        }
        return index;
    }
}

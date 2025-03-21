GC - mark, sweep, compact
System.gc();
Runtime.getRuntime().gc();

## GC

    - young
    	-edden  new oj created here
    	- surviour1 = when object are moved to here when minor gc  run when edden is full
    	- surviourto
    - old
    	- when max no surviour is done then moved here
    	- when old is full then major GC runs

    why mulitiple surviour = to avoid compacting step which is costly

- single thread
- concurrent - runs concurrently with ur prgram
- parallel -

finalize() is called when object is GC
it is called once per object, so initallized again in finalize next time before GC finalize() will not be called

- deprecated

## Memory

    - Stack
    	- smaller compared to heap memory
    	- store primitive data type
    	- scoped refreces
    	- store temp var
    	- thread store
    	- when its full stackoverflow error
    - heap
    	- string pool
    	- object
    	- when heap is full outof memmory error

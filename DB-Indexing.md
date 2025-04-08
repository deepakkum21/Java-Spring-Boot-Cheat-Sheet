# DB Indexing

![how data table is actually stored](./img/db-ind-data-stored.png)

![data page components](./img/data-page-components.png)

![data-page=example](./img/data-page-example.png)

- `DBMS creates and manage these data pages`.
- As for storing `1 table data, it can create many data pages`.
- These data pages ultimately gets stored in the Data Block[managed by Storage system] in physical memory like disk.

## What is Data block?

- Data Block is the `minimum amount of data which can be read/write by an I/O operation`.
- It is `managed by underlying storage system like disk`.
- Data Block `size can range from 4kb to 32kb (common size is 8KB)`.
- So based on the data block size, it `can hold 1 or many Data page`.
- DBMS `maintains the mapping of DataPage and Data Block`.

            Data Page1 -> Data Block 1
            Data Page2 -> Data Block 1
            Data Page3 -> Data Block 2
            Data Page4 -> Data Block 2

- `DBMS controls Data pages(like what Row goes in which page or sequence of pages etc.) but has no control on Data Blocks (data blocks can be scattered over the disk)`

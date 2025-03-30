# Spring Batch

Using Spring Batch: With Spring Batch, you can handle this efficiently and reliably by breaking it
down into manageable steps:

- `Chunk Processing: `
  - Spring Batch reads transactions for a few hundred customers at a time, processes them in chunks, and writes them to the statements in bulk.
- `Scheduled Execution: `
  - Set up the job to run during off-peak hours (like late at night), reducing the impact on the bank's primary systems.
- `Error Handling and Retry: `
  - If there's an error while processing a batch, Spring Batch can automatically retry the step or skip the problematic data and continue with the rest, logging any issues for further review.
- `Restart-ability: `
  - If a job fails midway through generating 10,000 statements, Spring Batch can restart from where it left off, ensuring every customer receives their statement without duplication.
- `Job Monitoring: `
  - With its built-in Job Repository, Spring Batch tracks each job's progress and stores metadata on completed, in-progress, and failed jobs, allowing easy monitoring and
    troubleshooting.

---

## Spring Batch Components

1. Job
2. Step
   - ItemReader
   - ItemProcessor
   - ItemWriter
3. JobExecution and StepExecution
4. JobRepository

---

## Imp Interfaces

- `Job Launcher`:Interface for launching a JOB
- `Job`: Defines a batch job.
- `Step`: Defines a single step in the job.
- `ItemReader`: Interface for reading input data.
- `ItemProcessor`: Interface for processing data.
- `ItemWriter`: Interface for writing output data.

```pgsql
+-------------------+      +-------------------+      +-------------------+
|                   |      |                   |      |                   |
|     JobLauncher   |----->|     JobRepository |----->|     JobExecution  |
|                   |      |                   |      |                   |
+-------------------+      +-------------------+      +-------------------+
                                |
                                |
                           +--------------------+
                           |                    |
                           |   Job Configuration |
                           |                    |
                           +--------------------+
                                |
                        +-------------------+
                        |                   |
                        |      Step 1       |   <-- ItemReader -> ItemProcessor -> ItemWriter
                        |                   |
                        +-------------------+
                                |
                        +-------------------+
                        |                   |
                        |      Step 2       |   <-- ItemReader -> ItemProcessor -> ItemWriter
                        |                   |
                        +-------------------+
                                |
                         [More Steps]
                                |
                        +-------------------+
                        |                   |
                        |      Step N       |   <-- ItemReader -> ItemProcessor -> ItemWriter
                        |                   |
                        +-------------------+
```

---

## Implementation

- From SpringBoot 3.0 and spring batch 5.0 `@EnableBatchProcessing not required`
- `Job Creation`

```java
    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        return new JobBuilder( name: "importPersons", jobRepository)
                        .start(step)
                        .build();
    }
```

- `Step Creation`

```java
    @Bean no usages
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder( name: "csv-import-step", jobRepository)
                    .<Person, Person>chunk( chunkSize: 10, transactionManager)
                    .reader(reader())
                    .processor(processor())
                    .writer(writer())
                    .build();
    }
```

- `Item Reader`

```java
            @Bean
            public FlatFileItemReader<Person> reader() {
                            return new FlatFileItemReaderBuilder<Person>()
                            .name("personItemReader")
                            .resource(new ClassPathResource("people-1000.csv"))
                            .linesToSkip(1)
                            .lineMapper(lineMapper())
                            .targetType(Person.class)
                            .build();
            }

            private LineMapper<Person> lineMapper() {
                DefaultLineMapper<Person> LineMapper = new DefaultLineMapper<>();

                DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
                lineTokenizer.setDelimiter(",");
                lineTokenizer.setStrict(false);
                lineTokenizer.setNames("id", "userId", "firstName", "lastName", "gender", "email", "phone", "date0fBirth");

                BeanWrapperFieldSetMapper<Person> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
                fieldSetMapper.setTargetType(Person.class);

                lineMapper.setLineTokenizer(lineTokenizer);
                lineMapper.setFieldSetMapper(fieldSetMapper);

                return lineMapper;
            }
```

- `Item Processor`

```java
            @Bean
            PersonProcessor processor() {
                return new PersonProcessor();
            }

            // place where before inserting data, some transformation can be done
            public class PersonProcessor implements ItemProcessor<Person, Person> {
                @Override
                public Person process(Person person) throws Exception {

                    person.setFirstName(person.getFirstName().toUpperCase());
                    person.setLastName(person.getLastName().toUpperCase());

                    return person;
                }
            }
```

- `item Writer`

```java
            @Bean
            RepositoryItemWriter<Person> writer() {
                RepositoryItemWriter<Person> writer = new RepositoryItemWriter<>();
                writer.setRepository(personRepository);
                writer.setMethodName("save");
                return writer;
            }
```

```java
public class JobController {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@PostMapping("/importData")
	public String jobLauncher() {

		final JobParameters jobParameters = new JobParametersBuilder()
				.addLong("startAt", System.currentTimeMillis()).toJobParameters();

			try {
				// Launch the job
				final JobExecution jobExecution = jobLauncher.run(job, jobParameters);

				// Return job status
				return jobExecution.getStatus().toString();
			} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
					| JobParametersInvalidException e) {
				e.printStackTrace();
				return "Job failed with exception: " + e.getMessage();
			}
		}
	}
```

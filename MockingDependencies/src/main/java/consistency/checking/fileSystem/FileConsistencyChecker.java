package consistency.checking.fileSystem;

import consistency.checking.InMemoryFileSystemChecksumDataStore;
import consistency.checking.contracts.IConsistencyChecker;
import consistency.checking.contracts.INotifier;
import consistency.checking.fileSystem.FileSystemUnit.FileSystemUnitType;
import consistency.checking.main.FileSystemHashGenerator;

public class FileConsistencyChecker implements IConsistencyChecker {


	private INotifier notifier;
	private InMemoryFileSystemChecksumDataStore checksumStore;
	private FileSystemDataStore fileStore;
	private FileSystemHashGenerator hashGenerator;

	
	// Dependency Inversion & Injection
	// FileSystemChecker get's all the required dependencies through it's constructor
	public FileConsistencyChecker(INotifier notifer,
			InMemoryFileSystemChecksumDataStore checksumStore, // for each file/directory holds the corresponding hash value
			FileSystemDataStore fileStore, // contains all the files and directories
			FileSystemHashGenerator hashGenerator // helps you to compute the hash of files/directories
			) {
		
		this.notifier=notifer;
		this.checksumStore=checksumStore;
		this.fileStore=fileStore;
		this.hashGenerator=hashGenerator;
		
	}

	/*
	 * 
	 * 
	 */
	@Override
	public void checkConsistency() {

		
		for(FileSystemUnit unit:fileStore) {
			
			// a FileSystemUnit can be a File or a Directory. It's an abstraction over different kind of File System entities 
			// hashGeneration gets a FileSystemUnit but internally it handles Files and Directories differently
			
			String newChecksum = this.hashGenerator.hash(unit); 
			
			// getting the previously computed checksum of the entity
			String oldChecksum=checksumStore.getDataUnit(unit.getPath()).getValue();
			
			if(!newChecksum.equals(oldChecksum)) {
				
				if(unit.getFileSystemUnitType()==FileSystemUnitType.Directory){
					// we use INotifier interface to notify the admin. The concrete implementations has been injected through constructor.
					this.notifier.notifyOnViolationDetectedOnFile("Violation on Directory, path: " +unit.getPath());
				}else if(unit.getFileSystemUnitType()==FileSystemUnitType.File){
					this.notifier.notifyOnViolationDetectedOnFile("Violation on File, path: " +unit.getPath());
				}
			}
				
			
		}
		
	}
	
}

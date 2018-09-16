package iblog.core.seq.repository;

import iblog.core.seq.exception.SequenceException;

public interface SequenceRepository {

	long getNextSequenceId(String key) throws SequenceException;

}
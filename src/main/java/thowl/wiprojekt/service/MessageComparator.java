package thowl.wiprojekt.service;

import thowl.wiprojekt.entity.Message;

import java.util.Comparator;

/**
 * Compares {@link Message}s by their creation time.
 *
 * @version 07.09.2023
 */
public class MessageComparator implements Comparator<Message> {

	/**
	 * Compares two {@link Message}s by comparing their creation
	 * {@link java.sql.Timestamp}s. Messages with a more recent Timestamp are
	 * assigned a higher priority than those with a lower one.
	 *
	 * @param msgA the first Message to be compared.
	 * @param msgB the second Message to be compared.
	 *
	 * @return -1 if the Timestamp of msgA is more recent than the one of
	 * msgB, 0 if they are both equal and 1 otherwise.
	 */
	@Override
	public int compare(Message msgA, Message msgB) {
		long aTime = msgA.getTime().getTime();
		long bTime = msgB.getTime().getTime();
		return (aTime == bTime) ? 0 : (aTime > bTime) ? -1 : 1;
	}
}

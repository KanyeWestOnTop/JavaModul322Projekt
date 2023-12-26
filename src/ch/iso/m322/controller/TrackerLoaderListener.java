package ch.iso.m322.controller;

import java.util.ArrayList;
import java.util.Iterator;

import ch.iso.m322.model.DataStoreTrack;

import ch.iso.m322.model.IDataStoreTracker;
import ch.iso.m322.model.Tracker;

public class TrackerLoaderListener implements Iterator<Tracker> {

	private ArrayList<Tracker> data;
	private int elm;
	
	public TrackerLoaderListener() {
		this.data = new ArrayList<Tracker>();
		this.elm = 0;
		
		loadData();
	}
	
	private void loadData() {
		IDataStoreTracker store = new DataStoreTrack();
		this.data = store.load(IDataStoreTracker.TRACK_LIST);
	}
	
		
	@Override
	public boolean hasNext() {
		return (this.elm < data.size());
	}

	@Override
	public Tracker next() {
		return data.get(this.elm++);
	}
	
}
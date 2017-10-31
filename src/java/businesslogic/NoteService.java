/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import dataaccess.NoteDB;
import dataaccess.NotesDBException;
import domainmodel.Note;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author 715583
 */
public class NoteService {
    
    private NoteDB ndb;

    public NoteService() {
        this.ndb = new NoteDB();
    }
    
    public Note getNote(int noteId) throws NotesDBException
    {
        return ndb.getNote(noteId);
    }
    
    public List<Note> getAll() throws NotesDBException
    {
        return ndb.getAll();
    }

    public int update(int noteId,String contents) throws NotesDBException
    {
        Note note = new Note(noteId);
        note.setContents(contents);
        return ndb.update(note);
    }
    public int delete(int noteId) throws NotesDBException
    {
        Note note = new Note();
        note.setNoteid(noteId);
        return ndb.delete(note);
    }
    public int insert(String contents) throws NotesDBException //insert new
    {
        //Date today = (Date) new java.util.Date();
        
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        
        Note note = new Note(0,sqlDate,contents);
         
         return ndb.insert(note);
    }
}

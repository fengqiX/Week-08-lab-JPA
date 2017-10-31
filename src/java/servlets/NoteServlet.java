/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.NoteService;
import dataaccess.NotesDBException;
import domainmodel.Note;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 715583
 */
public class NoteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String action = req.getParameter("action");
       NoteService ns = new NoteService();
       String url = "/WEB-INF/notes.jsp";
       
       if(action!=null && action.equals("delete"))
       {
           String noteId = req.getParameter("selectedNoteId");
           if(noteId==null) {}
           else
           {
               try {
                   ns.delete(Integer.parseInt(noteId));
               } catch (NotesDBException ex) {
                   Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       if(action!=null && action.equals("edit"))
       {
           String noteId =req.getParameter("id");
           String date = req.getParameter("date");
           String contents =req.getParameter("contents");
           System.out.println("noteid:"+noteId);
           if(noteId==null || noteId.isEmpty()) 
           {
               try {
                   ns.insert(contents);
               } catch (NotesDBException ex) {
                   Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
           else
           {
               try {
                   ns.update(Integer.parseInt(noteId), contents);
               } catch (NotesDBException ex) {
                   Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       List<Note> notesList=null;
        try {
            notesList = ns.getAll();
            if(notesList==null) notesList=new ArrayList<>();
        } catch (NotesDBException ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       req.setAttribute("notesList", notesList);
       
       getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String action = req.getParameter("action");
       NoteService ns = new NoteService();
       List<Note> notesList=null;
        try {
            notesList = ns.getAll();
        } catch (NotesDBException ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       if(notesList==null) notesList=new ArrayList<Note>();
       String url = "/WEB-INF/notes.jsp";
       
       req.setAttribute("notesList", notesList);
       
       if(action!=null && action.equals("view"))
       {
           
         //  System.out.println("selectednoteid"+req.getParameter("selectedNoteId"));
           int noteId = Integer.parseInt(req.getParameter("selectedNoteId"));
           Note note=null;
           try {
               note = ns.getNote(noteId);
           } catch (NotesDBException ex) {
               Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
           req.setAttribute("note", note);
       }
       getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

}

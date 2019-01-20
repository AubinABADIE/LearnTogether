package Types;

import java.io.Serializable;
import java.sql.Date;

/**
 * This class represents a department in the database.
 * @author Solène SERAFIN
 */

public class EventType implements Serializable {
    private int idEvent;
    private Date dateTimeEvent;
    private float duration;
    private int idRoom;
    private int idCourse;
    private int idTeacher;
    private int idClass;
    private int idPromo;
    private int idDepartement;

    public EventType(int idEvent, Date dateTimeEvent, float duration, int idRoom, int idCourse, int idTeacher, int idClass, int idPromo, int idDepartement){
        this.setIdEvent(idEvent);
        this.setDateTimeEvent(dateTimeEvent);
        this.setDuration(duration);
        this.setIdRoom(idRoom);
        this.setIdCourse(idCourse);
        this.setIdCourse(idTeacher);
        this.setIdClass(idClass);
        this.setIdPromo(idPromo);
        this.setIdDepartement(idDepartement);
    }

    public String toString(){
    	return "Event : " + idEvent;
    }
	public int getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

	public Date getDateTimeEvent() {
		return dateTimeEvent;
	}

	public void setDateTimeEvent(Date dateTimeEvent) {
		this.dateTimeEvent = dateTimeEvent;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public int getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}

	public int getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}

	public int getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(int idTeacher) {
		this.idTeacher = idTeacher;
	}

	public int getIdClass() {
		return idClass;
	}

	public void setIdClass(int idClass) {
		this.idClass = idClass;
	}

	public int getIdPromo() {
		return idPromo;
	}

	public void setIdPromo(int idPromo) {
		this.idPromo = idPromo;
	}

	public int getIdDepartement() {
		return idDepartement;
	}

	public void setIdDepartement(int idDepartement) {
		this.idDepartement = idDepartement;
	}
}
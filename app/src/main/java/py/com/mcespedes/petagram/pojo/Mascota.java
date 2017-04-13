package py.com.mcespedes.petagram.pojo;

/**
 * Created by root on 22/11/16.
 */

public class Mascota {

    private String id;
    private String nombre;
    private String imagen;
    private int likes;

    private String urlProfilePicture;
    private String fullNameProfile;

    public String getFullNameProfile() {
        return fullNameProfile;
    }

    public void setFullNameProfile(String fullNameProfile) {
        this.fullNameProfile = fullNameProfile;
    }

    public String getUrlProfilePicture() {
        return urlProfilePicture;
    }

    public void setUrlProfilePicture(String urlProfilePicture) {
        this.urlProfilePicture = urlProfilePicture;
    }

    public Mascota(String nombre, String imagen, int likes) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.likes = likes;
    }

    public Mascota(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}

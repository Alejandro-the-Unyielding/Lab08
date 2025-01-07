import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import it.unibo.deathnote.api.DeathNote;
import static it.unibo.deathnote.api.DeathNote.RULES;


public class DeathNoteImplementation implements DeathNote{

    private Map<String, Death> deathnote;




    public DeathNoteImplementation() {
        this.deathnote = new LinkedHashMap<>();
    }


    /**
     * Returns the rule with the given number.
     *
     * @param ruleNumber the number of the rule to return. The first rule has number one
     * @return the rule with the given number
     * @throws IllegalArgumentException if the given rule number is smaller than 1 or larger
     * than the number of rules
     */
    @Override
    public String getRule(int ruleNumber){
        Objects.nonNull(ruleNumber);
        try {
            return RULES.get(ruleNumber-1);
        } catch (Exception e) {
            throw new IllegalArgumentException("There is no " + ruleNumber + " rule", e);
        }    
    
    }

    /**
     * The human whose name is written in this DeathNote will die.
     * @param name the name of the human to kill
     * @throws NullPointerException if the given name is null.
     */
    public void writeName(String name){
        Objects.nonNull(name);
        try {
            deathnote.put(name, new Death(name, null, null));
        } catch (Exception e) {
            throw new IllegalArgumentException("The name must be s string", e);
        }
    }

    /**
     * If the cause of death is written within the next 40 milliseconds of writing the person's
     * name, it will happen.
     *
     * @param cause the cause of the human's death
     * @return true if the cause was written within 40 milliseconds, false otherwise
     * @throws IllegalStateException if there is no name written in this DeathNote,
     * or the cause is null
     */
    public boolean writeDeathCause(String cause){
        Objects.nonNull(cause);
        try {
            deathnote.values().stream().findFirst().get().setCause(cause);
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("The cause must be a string", e);
        }
    }

    /**
     * After writing the cause of death, details of the death should be written in the next
     * 6 seconds and 40 milliseconds.
     *
     * @param details the details of the human's death
     * @return true if the details were written within 6 seconds and 40 milliseconds, false otherwise
     * @throws IllegalStateException if there is no name written in this DeathNote,
     * or the details are null
     */
    public boolean writeDetails(String details){
        Objects.nonNull(details);
        try {
            deathnote.values().stream().findFirst().get().setDetails(details);
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("The details must be a string", e);
        }
    }

    /**
     * Provides the cause of death of the person with the given name.
     *
     * @param name the name of the person whose death cause to return
     * @return the death cause of the person with the given name.
     * If the cause of death is not specified, the method will return "heart attack".
     * @throws IllegalArgumentException if the provider name is not written in this DeathNote
     */
    public String getDeathCause(String name){
        Objects.nonNull(name);
        try {
            return deathnote.get(name).getCause();
        } catch (Exception e) {
            throw new IllegalArgumentException("The name must be written in the DeathNote", e);
        }
    }

    /**
     * Provides the details of the death of the person with the given name.
     *
     * @param name the name of the person whose death cause to return
     * @return the death details of the person with the given name,
     * or an empty string if no details have been provided.
     * @throws IllegalArgumentException if the provider name is not written in this DeathNote.
     */
    public String getDeathDetails(String name){
        Objects.nonNull(name);
        try {
            return deathnote.get(name).getDetails();
        } catch (Exception e) {
            throw new IllegalArgumentException("The name must be written in the DeathNote", e);
        }
    }

    /**
     * Checks if the given name is written in this DeathNote.
     *
     * @param name the name of the person
     * @return true if the given name is written in this DeathNote, false otherwise
     */
    public boolean isNameWritten(String name){
        Objects.nonNull(name);
        try {
            return deathnote.containsKey(name);
        } catch (Exception e) {
            throw new IllegalArgumentException("The name must be a string", e);
        }
    }

    
    private class Death{

        private String name;
        private String cause;
        private String details;

        public Death(String name, String cause, String details) {
            this.name = name;
            this.cause = cause;
            this.details = details;
        }

        public String getName() {
            return name;
        }

        public String getCause() {
            return cause;
        }

        public String getDetails() {
            return details;
        }

        public void setCause(String cause) {
            this.cause = cause;
        }

        public void setDetails(String details) {
            this.details = details;
        }

    }


}
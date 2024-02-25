public class Events extends Task{
    protected String dateTime;

    protected Date startDate;

    protected Date endDate;

    protected Time endTime;

    protected Time startTime;

    public String start;
    public  String end;

    public Events(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        processs(start);
        processe(end);
    }

    public void processs(String time) {
        String[] splitTD = time.split(" ");
        try {
            this.startDate = new Date(splitTD[0]);
            this.startTime = new Time(splitTD[1]);
        } catch (OrkException e) {

        }
    }

    public void processe(String time) {
        String[] splitTD = time.split(" ");
        try {
            this.endDate = new Date(splitTD[0]);
            this.endTime = new Time(splitTD[1]);
        } catch (OrkException e) {

        }
    }
    @Override
    public void print() {
        System.out.println("\t" + "[E]" + getStatusIcon() + getDescription() + "(from:" + startDate.toString()
                + " " + startTime.toString() + " to " + endDate.toString() + " " + endTime.toString() +")");
    }

    @Override
    public String notPrint() {
        return "\t" + "[E]" + getStatusIcon() + getDescription() + "(from:" + startDate.toString()
                + " " + startTime.toString() + " to " + endDate.toString() + " " + endTime.toString() +")";
    }

    @Override
    public String toString() {
        return isDone + " event " + getDescription() + "/from " + start + " /to " + end;
    }

    @Override
    public String type() {
        return ("[E]");
    }
}

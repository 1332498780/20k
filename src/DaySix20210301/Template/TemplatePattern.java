package DaySix20210301.Template;

public  abstract class TemplatePattern {
    abstract  void init();

    abstract  void ing();

    abstract  void end();

    public final void paly(){
        init();
        ing();
        end();
    }

}

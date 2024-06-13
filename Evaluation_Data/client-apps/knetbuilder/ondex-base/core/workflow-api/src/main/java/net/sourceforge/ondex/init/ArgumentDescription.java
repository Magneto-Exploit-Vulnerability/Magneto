package net.sourceforge.ondex.init;

/**
 * @author lysenkoa
 */
public class ArgumentDescription {
    //Class to which this argument should be parsed
    private String cls;
    //Name to be presented to the user
    private String name;
    //Internal names by which this argument is identified
    private String interanlName;
    //True position in the arguments that are parsed to the module
    private Integer inputId;
    //A position of the output represented by this named field in the array of outputs returned by the
    // producer. Only used if isOutputObject = true. If producer only returns one output the position 0 by
    //default and odes not need to be set
    private Integer outputId;
    //Default value, if any
    private String defaultValue;
    //Description - help information for the user
    private String description;
    //Is this argument necessary for the execution of the producer
    private boolean required = false;
    //An identifier that will convert the value of this argument to the representation of the cls
    private String parser;
    // Forces the passing of the raw value to the producer
    private Boolean isConfigurationArgument = false;
    // This argument identifies a global object that will be provided externally
    private boolean isInputObject = false;
    // this argument identifies a global object that will be generated by this producer
    private boolean isOutputObject = false;
    // type of argument
    private String type = "field";

    //GUI-related stuff
    //Additional custom instructions for formatting the GUI
    private String contentHint;
    // If true, this argument does not need a GUI
    private boolean doNotRender = false;

    public ArgumentDescription() {
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInteranlName() {
        return interanlName;
    }

    public void setInteranlName(String interanlName) {
        this.interanlName = interanlName;
    }

    public Integer getInputId() {
        return inputId;
    }

    public void setInputId(Integer id) {
        this.inputId = id;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsRequired() {
        return required;
    }

    public void setIsRequired(boolean required) {
        this.required = required;
    }

    public String getParser() {
        return parser;
    }

    public void setParser(String parser) {
        this.parser = parser;
    }

    public Boolean isConfigurationArgument() {
        return isConfigurationArgument;
    }

    public void setIsConfigurationArgument(Boolean isConfigurationArgument) {
        this.isConfigurationArgument = isConfigurationArgument;
    }

    public boolean isInputObject() {
        return isInputObject;
    }

    public void setIsInputObject(boolean isInputObject) {
        this.isInputObject = isInputObject;
    }

    public boolean isOutputObject() {
        return isOutputObject;
    }

    public void setIsOutputObject(boolean isOutputObject) {
        this.isOutputObject = isOutputObject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentHint() {
        return contentHint;
    }

    public void setContentHint(String contentHint) {
        this.contentHint = contentHint;
    }

    public boolean getDoNotRender() {
        return doNotRender;
    }

    public void setDoNotRender(boolean doNotRender) {
        this.doNotRender = doNotRender;
    }

    public Integer getOutputId() {
        return outputId;
    }

    public void setOutputId(Integer outputId) {
        this.outputId = outputId;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append("ArgumentDescription");
        sb.append("{name='").append(name).append('\'');
        sb.append(", cls='").append(cls).append('\'');
        sb.append(", required=").append(required);
        sb.append('}');
        return sb.toString();
    }
}
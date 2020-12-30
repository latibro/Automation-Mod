package latibro.automation.integration.immersiverailroading;

import latibro.automation.core.api.API;

public interface CabControlsAPI extends API {

    void setThrottleLevel(Number level);

    Number getThrottleLevel();

    void setBrakeLevel(Number level);

    Number getBrakeLevel();

    void setIgnitionState(Boolean state);

    Boolean getIgnitionState();

    void soundHorn();

}

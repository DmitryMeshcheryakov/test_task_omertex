package by.insight.test_task_omertex.presenter.vo;


import java.io.Serializable;
import by.insight.test_task_omertex.model.unsplash_model.PhotoDTO;
import by.insight.test_task_omertex.model.PlaceholderModelDTO;

public class ResultObject implements Serializable {

    private PhotoDTO mPhotoDTO;
    private PlaceholderModelDTO mPlaceholderModelDTO;

    public ResultObject(PhotoDTO photoDTO, PlaceholderModelDTO placeholderModelDTO) {
        this.mPhotoDTO = photoDTO;
        this.mPlaceholderModelDTO = placeholderModelDTO;
    }

    public PhotoDTO getPhotoDTO() {
        return mPhotoDTO;
    }

    public void setPhotoDTO(PhotoDTO photoDTO) {
        mPhotoDTO = photoDTO;
    }

    public PlaceholderModelDTO getPlaceholderModelDTO() {
        return mPlaceholderModelDTO;
    }

    public void setPlaceholderModelDTO(PlaceholderModelDTO placeholderModelDTO) {
        mPlaceholderModelDTO = placeholderModelDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultObject that = (ResultObject) o;

        if (!mPhotoDTO.equals(that.mPhotoDTO)) return false;
        return mPlaceholderModelDTO.equals(that.mPlaceholderModelDTO);

    }

    @Override
    public int hashCode() {
        int result = mPhotoDTO.hashCode();
        result = 31 * result + mPlaceholderModelDTO.hashCode();
        return result;
    }
}

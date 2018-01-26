package com.bilgeadam.edu.vkfmobil.model;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
/**
 * Created by ksmacpro on 28/07/2017.
 */

public class TiviBuModel {

    private String vodId;
    private String language;
    private String rating;
    private String yearOfRelease;
    private String country;
    private Price price;
    private Integer rentalDuration;
    private List<String> previewEcps = null;
    private List<String> movieEcps = null;
    private List<String> allowedEbcs = null;
    private String title;
    private String genre;
    private String posterUrl;
    private List<PosterUrl> posterUrls = null;
    private String serviceId;
    private String serviceType;
    private String providerId;
    private String offeringType;
    private ViewingWindowRange viewingWindowRange;
    private List<Object> associatedOfferings = null;
    private Boolean isNew;
    private List<String> categoryIds = null;
    private String mostSignificantSortValue;
    private String description;
    private Integer runTime;
    private String assetId;
    private String actors;
    private String directors;
    private String producers;
    private String studio;
    private String audioType;
    private Boolean videoHd;
    private List<Object> dubbedLanguages = null;
    private List<Object> subtitleLanguages = null;
    private Boolean hasPreview;
    private Boolean _new;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getVodId() {
        return vodId;
    }

    public void setVodId(String vodId) {
        this.vodId = vodId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Integer getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Integer rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public List<String> getPreviewEcps() {
        return previewEcps;
    }

    public void setPreviewEcps(List<String> previewEcps) {
        this.previewEcps = previewEcps;
    }

    public List<String> getMovieEcps() {
        return movieEcps;
    }

    public void setMovieEcps(List<String> movieEcps) {
        this.movieEcps = movieEcps;
    }

    public List<String> getAllowedEbcs() {
        return allowedEbcs;
    }

    public void setAllowedEbcs(List<String> allowedEbcs) {
        this.allowedEbcs = allowedEbcs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public List<PosterUrl> getPosterUrls() {
        return posterUrls;
    }

    public void setPosterUrls(List<PosterUrl> posterUrls) {
        this.posterUrls = posterUrls;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getOfferingType() {
        return offeringType;
    }

    public void setOfferingType(String offeringType) {
        this.offeringType = offeringType;
    }

    public ViewingWindowRange getViewingWindowRange() {
        return viewingWindowRange;
    }

    public void setViewingWindowRange(ViewingWindowRange viewingWindowRange) {
        this.viewingWindowRange = viewingWindowRange;
    }

    public List<Object> getAssociatedOfferings() {
        return associatedOfferings;
    }

    public void setAssociatedOfferings(List<Object> associatedOfferings) {
        this.associatedOfferings = associatedOfferings;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public List<String> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getMostSignificantSortValue() {
        return mostSignificantSortValue;
    }

    public void setMostSignificantSortValue(String mostSignificantSortValue) {
        this.mostSignificantSortValue = mostSignificantSortValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRunTime() {
        return runTime;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getAudioType() {
        return audioType;
    }

    public void setAudioType(String audioType) {
        this.audioType = audioType;
    }

    public Boolean getVideoHd() {
        return videoHd;
    }

    public void setVideoHd(Boolean videoHd) {
        this.videoHd = videoHd;
    }

    public List<Object> getDubbedLanguages() {
        return dubbedLanguages;
    }

    public void setDubbedLanguages(List<Object> dubbedLanguages) {
        this.dubbedLanguages = dubbedLanguages;
    }

    public List<Object> getSubtitleLanguages() {
        return subtitleLanguages;
    }

    public void setSubtitleLanguages(List<Object> subtitleLanguages) {
        this.subtitleLanguages = subtitleLanguages;
    }

    public Boolean getHasPreview() {
        return hasPreview;
    }

    public void setHasPreview(Boolean hasPreview) {
        this.hasPreview = hasPreview;
    }

    public Boolean getNew() {
        return _new;
    }

    public void setNew(Boolean _new) {
        this._new = _new;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }



    public class Price {

        private String currency;
        private Integer amount;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    public class ViewingWindowRange {

        private Integer startTime;
        private Integer stopTime;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public Integer getStartTime() {
            return startTime;
        }

        public void setStartTime(Integer startTime) {
            this.startTime = startTime;
        }

        public Integer getStopTime() {
            return stopTime;
        }

        public void setStopTime(Integer stopTime) {
            this.stopTime = stopTime;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }
    public class PosterUrl {

        private String name;
        private String value;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }
}



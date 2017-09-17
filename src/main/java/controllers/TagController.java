package controllers;

import api.ReceiptResponse;
import dao.TagDao;
import generated.tables.records.ReceiptsRecord;
import generated.tables.records.TagsRecord;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagController {

    final TagDao tags;

    public TagController(TagDao tags) {
        this.tags = tags;
    }


    // add a tag to a receipt
    @PUT
    @Path("/tags/{tag}")
    public void toggleTag(@PathParam("tag") String tagName, int receiptID) {

        if (tags.check(tagName, receiptID)) {
            tags.delete(tagName, receiptID);
        } else {
            tags.insert(tagName, receiptID);
        }

    }


    @GET
    @Path("/tags/{tag}")
    public List<ReceiptResponse> getTags (@PathParam("tag") String tagName) {
        List<Integer> tagRecords = tags.getTagReceiptsIDs(tagName);
        List<ReceiptsRecord> receipts = tags.getAllReceiptsbyIDs(tagRecords);

        return receipts.stream().map(ReceiptResponse::new).collect(toList());
    }

    @GET
    @Path("/netid")
    public String getNetID() {
        return "sdl83";
    }

    @GET
    public String getNetID2() {
        return "sdl83";
    }

}
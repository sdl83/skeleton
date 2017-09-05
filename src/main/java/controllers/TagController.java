package controllers;

//import api.CreateTagRequest;
import dao.TagDao;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagController {

    final TagDao tags;

    public TagController(TagDao tags) {
        this.tags = tags;
    }

    // TODO: add methods for GET and PUT

    // add a tag to a receipt
//    @PUT
//    @Path("/tags/{tag}")
//    public int toggleTag(@PathParam("tag") String tagName, @Valid @NotNull CreateTagRequest tag) {
//        return 99;
//        // <your code here
//    }


//    @POST
//    public int createReceipt(@Valid @NotNull CreateReceiptRequest receipt) {
//        return receipts.insert(receipt.merchant, receipt.amount);
//    }
//
//    public List<ReceiptResponse> getReceipts() {
//        List<ReceiptsRecord> receiptRecords = receipts.getAllReceipts();
//        return receiptRecords.stream().map(ReceiptResponse::new).collect(toList());
//    }

}
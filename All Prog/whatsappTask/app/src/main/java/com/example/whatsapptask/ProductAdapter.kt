package com.example.whatsapptask
class ProductAdapter(
    private val products: List<Product>,
    private val onQuantityChanged: (Int, Int) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.productImage)
        val name: TextView = view.findViewById(R.id.productName)
        val price: TextView = view.findViewById(R.id.productPrice)
        val quantity: EditText = view.findViewById(R.id.quantityEdit)
        val decrementButton: Button = view.findViewById(R.id.decrementButton)
        val incrementButton: Button = view.findViewById(R.id.incrementButton)
        val addButton: Button = view.findViewById(R.id.addButton)
        val subscribeButton: Button = view.findViewById(R.id.subscribeButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]

        holder.image.setImageResource(product.imageResId)
        holder.name.text = product.name
        holder.price.text = "Rs. ${product.price}"
        holder.subscribeButton.text = "SUBSCRIBE @ ₹${product.price}"

        var quantity = 1

        holder.decrementButton.setOnClickListener {
            if (quantity > 1) {
                quantity--
                holder.quantity.setText(quantity.toString())
            }
        }

        holder.incrementButton.setOnClickListener {
            quantity++
            holder.quantity.setText(quantity.toString())
        }

        holder.addButton.setOnClickListener {
            onQuantityChanged(position, quantity)
        }
    }

    override fun getItemCount() = products.size
}
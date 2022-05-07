package pl.edu.wszib.order.infrastructure.configuration;

import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Set;

@ConfigurationProperties(prefix = "orders.products")
public class ProductProperties {
    private Set<ProductInitialProperties> initials;

    public Set<ProductInitialProperties> getInitials() {
        return initials;
    }

    public void setInitials(final Set<ProductInitialProperties> initials) {
        this.initials = initials;
    }

    public static class ProductInitialProperties {

        @NotNull
        private String id;

        @NotNull
        @Length(min = 2, max = 50)
        private String name;

        //można docelowo przejść na:
        //https://github.com/JavaMoney/jsr354-api
        @NotNull
        @Positive
        private BigDecimal price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }

}
